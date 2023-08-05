import { JobPostDTO } from "@/model/JobPost";

export async function submitJob(newJob: JobPostDTO){
    // const res = await fetch("https://random-data-api.com/api/v2/users");
    const res = await fetch(`${process.env.BASE_URL}/addpost`, {
        method: "POST",
        body: JSON.stringify(newJob),
        headers:{
            "Content-type": "application/json"
        }
    })
    const data = await res.json();
    console.log(data);
}