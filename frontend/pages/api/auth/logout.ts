import type { NextApiRequest, NextApiResponse } from "next";

export default function logoutHandler(
  req: NextApiRequest,
  res: NextApiResponse,
) {
  if (req.method === "POST") {
    // Setze das authToken-Cookie auf leer und sorge dafür, dass es abläuft
    res.setHeader("Set-Cookie", "authToken=; Max-Age=0; path=/; HttpOnly");
    return res.status(200).json({ message: "Logout erfolgreich" });
  } else {
    res.setHeader("Allow", ["POST"]);
    return res
      .status(405)
      .json({ message: `Method ${req.method} Not Allowed` });
  }
}
