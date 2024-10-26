import type { NextApiRequest, NextApiResponse } from "next";

const IAM_SERIVCE_URL = "http://localhost:8081/auth/login";

export default async function loginHandler(
  req: NextApiRequest,
  res: NextApiResponse,
) {
  if (req.method === "POST") {
    const { username, password } = req.body;

    try {
      const response = await fetch(IAM_SERIVCE_URL, {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({ username: username, password: password }),
      });

      if (response.ok) {
        const { token } = await response.json();

        res.setHeader("Set-Cookie", `authToken=${token}; HttpOnly; Path=/;`);

        return res.status(200).json({ message: "Login erfolgreich" });
      } else {
        const errorData = await response.json();
        return res.status(401).json({ message: errorData.message || "Ungültige Anmeldeinformationen" });
      }
    } catch (error) {
      console.error("Error during IAM service call:", error);
      return res.status(500).json({ message: "Server error during login" });
    }

  } else {
    res.setHeader("Allow", ["POST"]);
    return res
      .status(405)
      .json({ message: `Method ${req.method} Not Allowed` });
  }
}
