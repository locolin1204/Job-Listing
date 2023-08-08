import { JobPostDTO } from "@/model/JobPost";

export async function getAllJobs(): Promise<Array<JobPostDTO> | null> {
	try {
		const res = await fetch(`${process.env.VUE_APP_BASE_URL}/allposts`, {
			method: "GET",
		});
		const status = await res.status;
        const data = await res.json();
		if (status === 200) {
			return data;
		} else {
			return null;
		}
	} catch (error) {
		console.log(error);
		return null;
	}
}

export async function getJobsByKeyword(keyword: string):  Promise<Array<JobPostDTO> | null> {
	try {
		const res = await fetch(`${process.env.VUE_APP_BASE_URL}/posts/${keyword}`, {
			method: "GET",
		});
		const status = await res.status;
        const data = await res.json();
		if (status === 200) {
			return data;
		} else {
			return null;
		}
	} catch (error) {
		console.log(error);
		return null;
	}
}