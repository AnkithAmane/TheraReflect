"use client";

import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Button } from "@/components/ui/button"
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group"
import Link from "next/link"

export default function SignUpPage() {
  return (
    <div className="flex min-h-screen items-center justify-center px-4 py-12">
      <Card className="w-full max-w-lg">
        <CardHeader className="text-center">
          <CardTitle className="text-2xl font-bold tracking-tight text-stone-900">
            Create Your Account
          </CardTitle>
          <CardDescription className="text-stone-600">
            Join TheraReflect and start your journey with us.
          </CardDescription>
        </CardHeader>
        <CardContent>
          <div className="grid gap-6">
            
            {/* Account Type Selection */}
            <div className="grid gap-2">
              <Label>I am signing up as a...</Label>
              <RadioGroup defaultValue="user" className="grid grid-cols-2 gap-4">
                <div>
                  <RadioGroupItem value="user" id="user" className="peer sr-only" />
                  <Label
                    htmlFor="user"
                    className="flex flex-col items-center justify-between rounded-md border-2 border-muted bg-popover p-4 hover:bg-accent hover:text-accent-foreground peer-data-[state=checked]:border-blue-500 [&:has([data-state=checked])]:border-blue-500 cursor-pointer"
                  >
                    User
                  </Label>
                </div>
                <div>
                  <RadioGroupItem value="therapist" id="therapist" className="peer sr-only" />
                  <Label
                    htmlFor="therapist"
                    className="flex flex-col items-center justify-between rounded-md border-2 border-muted bg-popover p-4 hover:bg-accent hover:text-accent-foreground peer-data-[state=checked]:border-teal-500 [&:has([data-state=checked])]:border-teal-500 cursor-pointer"
                  >
                    Therapist
                  </Label>
                </div>
              </RadioGroup>
            </div>

            <div className="grid gap-2">
              <Label htmlFor="full-name">Full Name</Label>
              <Input id="full-name" placeholder="John Doe" required />
            </div>

            <div className="grid gap-2">
              <Label htmlFor="email">Email</Label>
              <Input
                id="email"
                type="email"
                placeholder="you@example.com"
                required
              />
            </div>

            <div className="grid gap-2">
              <Label htmlFor="password">Password</Label>
              <Input id="password" type="password" required />
            </div>
            
            <Button type="submit" className="w-full bg-teal-500 text-white hover:bg-teal-600">
              Create Account
            </Button>
          </div>
          {/* <div className="mt-6 text-center text-sm">
            Already have an account?{" "}
            <Link href="/user/login" className="underline font-medium text-teal-600 hover:text-teal-700">
              Sign in
            </Link>
          </div> */}
        </CardContent>
      </Card>
    </div>
  )
}
