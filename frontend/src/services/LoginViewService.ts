import { UserDTO } from "@/model/User";
import axios, { Axios, AxiosResponse } from "axios";
import { StatusCodes } from "http-status-codes";

export async function userLogin(
	email: string,
	password: string
): Promise<AxiosResponse | null> {
	try {
		const url = new URL(
			`${process.env.VUE_APP_BASE_URL}/api/v1/auth/authenticate`
		);
		const requestBody = {
			email: email,
			password: password,
		};
		const res = await axios.post(url.toString(), requestBody);
		const status = await res.status;
		const data = await res.data;
		return res;
	} catch (error) {
		console.error(error);
		return null;
	}
}

export async function verifyToken(
	token: string
): Promise<AxiosResponse | null> {
	const url = new URL(
		`${process.env.VUE_APP_BASE_URL}/api/v1/auth/authenticate-token`
	);
	try {
		const headers = {
			headers: {
				token: token,
			},
		};
		const res = await axios.get(url.toString(), headers);
		return res;
	} catch (error) {
		console.error(error);
		return null;
	}
}
