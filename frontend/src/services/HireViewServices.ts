import { JobPostDTO } from "@/model/JobPost";
import axios, { AxiosResponse } from "axios";

export async function submitJobAndResponse(
	newJob: JobPostDTO
): Promise<AxiosResponse> {
	try {
		const url = new URL(`${process.env.VUE_APP_BASE_URL}/api/v1/job/addpost`);
		const body = JSON.stringify(newJob);
		const res = await axios.post(url.toString(), body);
		return res;
	} catch (error) {
		console.error(error);
		throw error;
	}
}
