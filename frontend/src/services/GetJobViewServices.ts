import { JobPostDTO } from "@/model/JobPost";
import axios from 'axios';

export async function getAllJobs(): Promise<Array<JobPostDTO> | null> {
	try {
		const url = new URL(`${process.env.VUE_APP_BASE_URL}/allposts`);
		const res = await axios.get(url.toString());
		const status = await res.status;
		const data = await res.data;
		
		if (status === 200) return data;
		else return null;
	} catch (error) {
		console.error(error);
		return null;
	}
}

export async function getJobsByKeywordAndFilter(
	keyword: string,
	techList: Array<string>
): Promise<Array<JobPostDTO> | null> {
	try {

		const url = new URL(`${process.env.VUE_APP_BASE_URL}/searchAndFilterPost`);
		const params = new URLSearchParams();
		params.append("tech-list", techList.join(","));
		params.append("keyword", keyword)
	
		const res = await axios.get(url.toString(), { params } );
		const status = await res.status;
		const data = await res.data;

		if (status === 200) return data;
		else return null;
		
	} catch (error) {
		console.error(error);
		return null;
	}
}

export async function getAllTechs(): Promise<Array<string> | null> {
	try {
		const url = `${process.env.VUE_APP_BASE_URL}/getAllTechs`;
		const res = await axios.get(url.toString());
		const status = await res.status;
		const data = await res.data;
		
		if (status === 200) return data;
		else return null;
	} catch (error) {
		console.error(error);
		return null;
	}
}
