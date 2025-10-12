import React from 'react';
import Link from 'next/link'; // Use Next.js Link for client-side navigation
import { cn } from "@/lib/utils";

// A simple SVG logo component
const TheraReflectLogo = () => (
    <div className="flex items-center gap-2">
        <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M12 21.35L10.55 20.03C5.4 15.36 2 12.28 2 8.5C2 5.42 4.42 3 7.5 3C9.24 3 10.91 3.81 12 5.09C13.09 3.81 14.76 3 16.5 3C19.58 3 22 5.42 22 8.5C22 12.28 18.6 15.36 13.45 20.04L12 21.35Z" fill="currentColor" className="text-teal-500"/>
        </svg>
        <span className="font-bold text-xl text-stone-800">TheraReflect</span>
    </div>
);


export function Navbar() {
  const navItems = [
    { name: 'Home', href: '/' },
    { name: 'About Us', href: '/about' },
    { name: 'Contact', href: '/contact' },
    // Add more links here later
  ];

  return (
    <header className="fixed top-0 left-0 right-0 z-50 bg-white/80 backdrop-blur-sm shadow-sm">
      <nav className="container mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex items-center justify-between h-16">
          {/* Logo */}
          <Link href="/">
              <TheraReflectLogo />
          </Link>

          {/* Navigation Links */}
          <div className="hidden md:flex items-center space-x-8">
            {navItems.map((item) => (
              <Link key={item.name} href={item.href} className="text-sm font-medium text-stone-600 hover:text-teal-600 transition-colors">
                {item.name}
              </Link>
            ))}
          </div>

           {/* Mobile Menu Button can be added here later */}

        </div>
      </nav>
    </header>
  );
}
