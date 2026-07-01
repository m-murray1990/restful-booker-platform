import { defineConfig } from "eslint/config";
import nextConfig from "eslint-config-next";

export default defineConfig([
  ...nextConfig,
  {
    rules: {
      "react-hooks/set-state-in-effect": "off"
    }
  },
  {
    files: ["src/__tests__/**/*.{ts,tsx}"],
    rules: {
      "react/display-name": "off"
    }
  },
  {
    files: [
      "src/app/cookie/page.tsx",
      "src/app/privacy/page.tsx",
      "src/components/home/HotelContact.tsx"
    ],
    rules: {
      "react/no-unescaped-entities": "off"
    }
  }
]);
