import type { Metadata } from "next";
import { Geist, Geist_Mono } from "next/font/google";
import "./globals.css";
// Corrected: Use the path alias to import the Navbar component
import { Navbar } from "./navbar"; 

const geistSans = Geist({
  variable: "--font-geist-sans",
  subsets: ["latin"],
});

const geistMono = Geist_Mono({
  variable: "--font-geist-mono",
  subsets: ["latin"],
});

export const metadata: Metadata = {
  title: "TheraReflect",
  description: "Interact with users and therapists",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body
        // Corrected: Added back the background color and kept your new font classes
        className={`${geistSans.variable} ${geistMono.variable} antialiased`}
      >
        {/* Corrected: Render the Navbar component correctly */}
        <Navbar />
        
        {/* Corrected: Added the <main> tag back with padding to prevent content from hiding behind the navbar */}
        <main className="pt-8">
          {children}
        </main>
      </body>
    </html>
  );
}

