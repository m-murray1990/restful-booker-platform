
import React from 'react';
import Link from 'next/link';
import { Branding } from "@/types/branding";

interface NavProps {
    branding: Branding;
}

const HomeNav: React.FC<NavProps> = ({ branding }) => {

    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-white shadow-sm sticky-top">
            <div className="container">
            <Link className="navbar-brand d-flex align-items-center" href="/">
                <span>{branding.name}</span>
            </Link>
            <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarNav">
                <ul className="navbar-nav ms-auto">
                <li className="nav-item">
                    <Link className="nav-link" href="/#rooms">Rooms</Link>
                </li>
                <li className="nav-item">
                    <Link className="nav-link" href="/#booking">Booking</Link>
                </li>
                <li className="nav-item">
                    <Link className="nav-link" href="/#amenities">Amenities</Link>
                </li>
                <li className="nav-item">
                    <Link className="nav-link" href="/#location">Location</Link>
                </li>
                <li className="nav-item">
                    <Link className="nav-link" href="/#contact">Contact</Link>
                </li>
                <li className="nav-item">
                    <Link className="nav-link" href="/admin">Admin</Link>
                </li>
                </ul>
            </div>
            </div>
        </nav>
    );

}

export default HomeNav;
