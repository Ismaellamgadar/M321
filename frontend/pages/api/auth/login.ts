import type { NextApiRequest, NextApiResponse } from "next";

export default async function loginHandler(
  req: NextApiRequest,
  res: NextApiResponse,
) {
  if (req.method === "POST") {
    const { email, password } = req.body;
    if (email === "test@example.com" && password === "password") {
      res.setHeader("Set-Cookie", "authToken=12345; HttpOnly; Path=/");
      return res.status(200).json({ message: "Login erfolgreich" });
    }
    return res.status(401).json({ message: "Ungültige Anmeldeinformationen" });
  } else {
    res.setHeader("Allow", ["POST"]);
    return res
      .status(405)
      .json({ message: `Method ${req.method} Not Allowed` });
  }
}
