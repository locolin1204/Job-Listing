import { JobPostDTO } from "@/model/JobPost";

export async function submitJob(newJob: JobPostDTO): Promise<boolean>{
    // const res = await fetch("https://random-data-api.com/api/v2/users");
    // const data = await res.json();
    // console.log("res", res);
    // console.log("data", data);
    // await new Promise(r => setTimeout(r, 1000));
    // return false;

    try {
        const res = await fetch(`${process.env.VUE_APP_BASE_URL}/addpost`, {
            method: "POST",
            body: JSON.stringify(newJob),
            headers:{
                "Content-type": "application/json"
            }
        })
        const status = await res.status;
        if (status === 200){
            return true
        }
        else {
            return false
        }
    } catch (error) {
        console.log(error);
        return false
    }
}