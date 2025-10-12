import React from 'react';
import Link from 'next/link'; // Corrected: Link is the default export
import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";

// --- SVG Icons ---

// Helper component for User Icon
const UserIcon = (props: React.SVGProps<SVGSVGElement>) => (
  <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" {...props}><path d="M19 21v-2a4 4 0 0 0-4-4H9a4 4 0 0 0-4 4v2" /><circle cx="12" cy="7" r="4" /></svg>
);

// Cleaned up and more distinct icon for a Therapist/Health Professional
const TherapistIcon = (props: React.SVGProps<SVGSVGElement>) => (
    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" {...props}>
        <path d="M12 21.35l-1.45-1.32C5.4 15.36 2 12.28 2 8.5 2 5.42 4.42 3 7.5 3c1.74 0 3.41.81 4.5 2.09C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5c0 3.78-3.4 6.86-8.55 11.54L12 21.35z"/>
        <line x1="12" y1="8" x2="12" y2="14"/>
        <line x1="9" y1="11" x2="15" y2="11"/>
    </svg>
);


export default function Home() {
  return (
    <main className="flex flex-col items-center justify-center min-h-screen text-stone-800 p-4 sm:p-6">
      <div className="text-center mb-12">
        <h1 className="flex flex-col items-center font-bold tracking-tight text-stone-900">
          <span className="text-4xl sm:text-5xl">
            Welcome to
          </span>
          <span className="text-6xl sm:text-7xl text-teal-600 mt-1">
            TheraReflect
          </span>
        </h1>
        <p className="mt-3 text-lg text-stone-600">
          A reflective space for growth and guidance.
        </p>
      </div>

      <div className="w-full max-w-4xl mx-auto">
        <h2 className="text-xl text-center font-medium mb-6 text-stone-700">How would you like to join us?</h2>
        <div className="grid grid-cols-1 md:grid-cols-2 gap-8">

          {/* User Card */}
          <Card className="w-full transform transition-transform duration-300 hover:scale-105 hover:shadow-xl">
            <CardHeader>
              <CardTitle className="flex items-center gap-3">
                <UserIcon className="text-blue-500" />
                <span>I'm Seeking Guidance</span>
              </CardTitle>
              <CardDescription>
                Start your journey towards mental wellness. Connect with professionals in a safe space.
              </CardDescription>
            </CardHeader>
            <CardContent>
              <div className="text-sm text-stone-600 space-y-2">
                <p>✓ Find the right therapist for you</p>
                <p>✓ Schedule sessions with ease</p>
                <p>✓ Access resources and tools</p>
              </div>
            </CardContent>
            <CardFooter>
              {/* Corrected: The text content must be INSIDE the Link component when using asChild */}
              <Button asChild size="lg" className="w-full bg-blue-500 text-white hover:bg-blue-600">
                <Link href="/userLogin">
                  Login / Sign Up as a User
                </Link>
              </Button>
            </CardFooter>
          </Card>

          {/* Therapist Card */}
          <Card className="w-full transform transition-transform duration-300 hover:scale-105 hover:shadow-xl">
            <CardHeader>
              <CardTitle className="flex items-center gap-3">
                <TherapistIcon className="text-teal-500" />
                <span>I'm a Therapist</span>
              </CardTitle>
              <CardDescription>
                Manage your clients, schedule, and resources all in one place. Join our community of professionals.
              </CardDescription>
            </CardHeader>
            <CardContent>
              <div className="text-sm text-stone-600 space-y-2">
                <p>✓ Connect with new clients</p>
                <p>✓ Streamline your workflow</p>
                <p>✓ Share your expertise</p>
              </div>
            </CardContent>
            <CardFooter>
               {/* Corrected: Added a link for the therapist login */}
              <Button asChild size="lg" className="w-full bg-teal-500 text-white hover:bg-teal-600">
                <Link href="/therapist/login">
                  Login / Sign Up as a Therapist
                </Link>
              </Button>
            </CardFooter>
          </Card>

        </div>
      </div>
    </main>
  );
}

