import { JobPostDTO } from "@/model/JobPost";
import axios from "axios";

export async function submitJob(newJob: JobPostDTO): Promise<boolean>{
    try {
        const url = new URL(`${process.env.VUE_APP_BASE_URL}/addpost`);
        const body = JSON.stringify(newJob);
		const res = await axios.post(url.toString(), body);
		const status = await res.status;
        console.log("body", body);
        console.log("res", res);
        console.log("status", status);
        
        if (status === 201) return true
        else return false
        
    } catch (error) {
        console.log(error);
        return false
    }
}