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
import Link from "next/link"

export default function UserLoginPage() {
  return (
    <div className="flex min-h-screen items-center justify-center px-4">
      {/* Changed max-w-md to max-w-lg to make the card wider */}
      <Card className="w-full max-w-lg">
        <CardHeader className="text-center">
          <CardTitle className="text-2xl font-bold tracking-tight text-stone-900">
            Welcome Back
          </CardTitle>
          <CardDescription className="text-stone-600">
            Sign in to your TheraReflect account
          </CardDescription>
        </CardHeader>
        <CardContent>
          {/* Changed gap-4 to gap-6 for more vertical spacing */}
          <div className="grid gap-6">
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
              <div className="flex items-center">
                <Label htmlFor="password">Password</Label>
                <Link
                  href="#" // Link to a future "Forgot Password" page
                  className="ml-auto inline-block text-sm underline text-stone-600 hover:text-teal-600"
                >
                  Forgot your password?
                </Link>
              </div>
              <Input id="password" type="password" required />
            </div>
            <Button type="submit" className="w-full bg-blue-500 text-white hover:bg-blue-600">
              Login
            </Button>
          </div>
          <div className="mt-6 text-center text-sm">
            Don&apos;t have an account?{" "}
            <Link href="/signup" className="underline font-medium text-teal-600 hover:text-teal-700">
              Sign up
            </Link>
          </div>
        </CardContent>
      </Card>
    </div>
  )
}

