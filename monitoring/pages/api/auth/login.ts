import type { NextApiRequest, NextApiResponse } from "next";

const IAM_SERIVCE_URL = process.env.IAM_SERVICE_URL! || "http://localhost:8081";

export default async function loginHandler(
  req: NextApiRequest,
  res: NextApiResponse,
) {
  if (req.method === "POST") {
    const { username, password } = req.body;

    try {
      const response = await fetch(IAM_SERIVCE_URL + "/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }),
      });

      if (response.ok) {
        const { token } = await response.json();

        res.setHeader("Set-Cookie", `authToken=${token}; HttpOnly; Path=/;`);

        return res.status(200).json({ message: "Login erfolgreich" });
      } else {
        let errorData = {};
        try {
          errorData = await response.json();
        } catch (err) {
          console.warn("Non-JSON response received from IAM service:", err);
          errorData = { message: "Ungültige Anmeldeinformationen" };
        }
        console.error("IAM service error:", errorData);
        return res.status(401).json({ message: errorData || "Error" });
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
