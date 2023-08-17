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
		console.error(error);
		return null;
	}
}

// export async function getJobsByKeyword(
// 	keyword: string
// ): Promise<Array<JobPostDTO> | null> {
// 	try {
// 		const res = await fetch(
// 			`${process.env.VUE_APP_BASE_URL}/posts/${keyword}`,
// 			{
// 				method: "GET",
// 			}
// 		);
// 		const status = await res.status;
// 		const data = await res.json();
// 		if (status === 200) {
// 			return data;
// 		} else {
// 			return null;
// 		}
// 	} catch (error) {
// 		console.error(error);
// 		return null;
// 	}
// }

export async function getJobsByKeywordAndFilter(
	keyword: string,
	techList: Array<string>
): Promise<Array<JobPostDTO> | null> {
	try {
		const url = new URL(`${process.env.VUE_APP_BASE_URL}/searchAndFilterPost/${keyword}`);

		const param = new URLSearchParams();
		param.append("tech-list", techList.join(","));
		url.search = param.toString();
		console.log(url);
		const res = await fetch(url.toString(), {
			method: "GET",
		});
		/// to do request para
		const status = await res.status;
		const data = await res.json();
		if (status === 200) {
			return data;
		} else {
			return null;
		}
	} catch (error) {
		console.error(error);
		return null;
	}
}

export async function getAllTechs(): Promise<Array<string> | null> {
	try {
		const res = await fetch(`${process.env.VUE_APP_BASE_URL}/getAllTechs`, {
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
		console.error(error);
		return null;
	}
}

export async function filterTechPosts(): Promise<Array<JobPostDTO> | null> {
	try {
		const res = await fetch(`${process.env.VUE_APP_BASE_URL}/filterTechPosts`, {
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
		console.error(error);
		return null;
	}
}
