import React from 'react';
import { render, screen, waitFor, fireEvent } from '@testing-library/react';
import '@testing-library/jest-dom';
import Availability from '../components/home/Availability';

jest.mock('react-datepicker', () => {
    const MockDatePicker = ({ selected, onChange, className }: {
        selected: Date | null;
        onChange: (date: Date | null) => void;
        className?: string;
    }) => (
        <input
            type="text"
            className={className}
            value={selected ? selected.toISOString().split('T')[0] : ''}
            onChange={(e) => onChange(e.target.value ? new Date(e.target.value) : null)}
            data-testid="date-picker"
        />
    );
    MockDatePicker.displayName = 'DatePicker';
    return {
        __esModule: true,
        default: MockDatePicker,
        registerLocale: jest.fn(),
        setDefaultLocale: jest.fn(),
    };
});

jest.mock('date-fns/locale/en-GB', () => ({ enGB: {} }));

const mockRooms = [
    {
        roomid: 1,
        roomName: '101',
        type: 'Single',
        accessible: false,
        image: '/images/room.jpg',
        description: 'A cozy single room',
        features: ['WiFi', 'TV'],
        roomPrice: 100,
    },
    {
        roomid: 2,
        roomName: '102',
        type: 'Double',
        accessible: true,
        image: '/images/room2.jpg',
        description: 'A spacious double room',
        features: ['WiFi', 'Safe'],
        roomPrice: 150,
    },
];

describe('Availability Component', () => {
    beforeEach(() => {
        jest.clearAllMocks();
        global.fetch = jest.fn().mockResolvedValue({
            ok: true,
            json: () => Promise.resolve({ rooms: mockRooms }),
        });
    });

    it('renders the Check In label', () => {
        render(<Availability />);
        expect(screen.getByText('Check In')).toBeInTheDocument();
    });

    it('renders the Check Out label', () => {
        render(<Availability />);
        expect(screen.getByText('Check Out')).toBeInTheDocument();
    });

    it('renders the Check Availability button', () => {
        render(<Availability />);
        expect(screen.getByRole('button', { name: 'Check Availability' })).toBeInTheDocument();
    });

    it('renders the Our Rooms section heading', () => {
        render(<Availability />);
        expect(screen.getByText('Our Rooms')).toBeInTheDocument();
    });

    it('fetches and displays rooms on initial load', async () => {
        render(<Availability />);
        await waitFor(() => {
            expect(screen.getByText('Single')).toBeInTheDocument();
            expect(screen.getByText('Double')).toBeInTheDocument();
        });
    });

    it('calls the rooms API on initial load', async () => {
        render(<Availability />);
        await waitFor(() => {
            expect(global.fetch).toHaveBeenCalledWith('/api/room');
        });
    });

    it('calls availability API with checkin and checkout params when button is clicked', async () => {
        render(<Availability />);
        await waitFor(() => expect(global.fetch).toHaveBeenCalledTimes(1));

        fireEvent.click(screen.getByRole('button', { name: 'Check Availability' }));

        await waitFor(() => {
            expect(global.fetch).toHaveBeenCalledTimes(2);
            const lastCallUrl = (global.fetch as jest.Mock).mock.calls[1][0] as string;
            expect(lastCallUrl).toContain('/api/room?checkin=');
            expect(lastCallUrl).toContain('&checkout=');
        });
    });

    it('updates displayed rooms after checking availability', async () => {
        const filteredRooms = [mockRooms[0]];
        (global.fetch as jest.Mock)
            .mockResolvedValueOnce({ ok: true, json: () => Promise.resolve({ rooms: mockRooms }) })
            .mockResolvedValueOnce({ ok: true, json: () => Promise.resolve({ rooms: filteredRooms }) });

        render(<Availability />);
        await waitFor(() => expect(screen.getByText('Double')).toBeInTheDocument());

        fireEvent.click(screen.getByRole('button', { name: 'Check Availability' }));

        await waitFor(() => {
            expect(screen.queryByText('Double')).not.toBeInTheDocument();
            expect(screen.getByText('Single')).toBeInTheDocument();
        });
    });

    it('passes date query string to Book now links', async () => {
        render(<Availability />);
        await waitFor(() => {
            const bookLinks = screen.getAllByText('Book now');
            const href = bookLinks[0].closest('a')?.getAttribute('href') ?? '';
            expect(href).toContain('checkin=');
            expect(href).toContain('checkout=');
        });
    });

    it('updates check-in date when date picker changes', async () => {
        render(<Availability />);
        const datePickers = screen.getAllByTestId('date-picker');
        const checkInPicker = datePickers[0];

        fireEvent.change(checkInPicker, { target: { value: '2026-07-01' } });

        await waitFor(() => {
            expect(checkInPicker).toHaveValue('2026-07-01');
        });
    });

    it('updates check-out date when date picker changes', async () => {
        render(<Availability />);
        const datePickers = screen.getAllByTestId('date-picker');
        const checkOutPicker = datePickers[1];

        fireEvent.change(checkOutPicker, { target: { value: '2026-07-05' } });

        await waitFor(() => {
            expect(checkOutPicker).toHaveValue('2026-07-05');
        });
    });
});
