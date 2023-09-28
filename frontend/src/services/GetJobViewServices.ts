import { JobPostDTO } from "@/model/JobPost";
import axios, { AxiosResponse } from "axios";
import { StatusCodes } from "http-status-codes";

export async function getAllJobs(): Promise<Array<JobPostDTO> | null> {
	try {
		const url = new URL(`${process.env.VUE_APP_BASE_URL}/api/v1/job/posts`);
		const res = await axios.get(url.toString());
		const status = await res.status;
		const data = await res.data;

		if (status === StatusCodes.OK) return data;
		else return null;
	} catch (error) {
		console.error(error);
		return null;
	}
}

export async function jobsByKeywordAndFilterResponse(
	keyword: string,
	techList: Array<string>
): Promise<AxiosResponse> {
	try {
		const url = new URL(`${process.env.VUE_APP_BASE_URL}/api/v1/job/search-filter-posts`);
		const params = new URLSearchParams();
		params.append("tech-list", techList.join(","));
		params.append("keyword", keyword);

		const res = await axios.get(url.toString(), { params });
		const status = await res.status;
		const data = await res.data;

		// if (status === StatusCodes.OK) return data;
		// else return null;
		return res;
	} catch (error) {
		console.error(error);
		// return null;
		throw error;
	}
}

export async function getAllTechs(): Promise<Array<string> | null> {
	try {
		const url = `${process.env.VUE_APP_BASE_URL}/api/v1/job/techs`;
		const res = await axios.get(url.toString());
		const status = await res.status;
		const data = await res.data;

		if (status === StatusCodes.OK) return data;
		else return null;
	} catch (error) {
		console.error(error);
		return null;
	}
}
