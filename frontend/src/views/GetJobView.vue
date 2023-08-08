<template>
	<div class="get-job">
		<h1>Get a Job!</h1>
		<p>Search for a job</p>
		<input
			type="text"
			placeholder="e.g. Software Engineer"
			v-model="searchText"
		/>
	</div>
	<div class="job-list">
		<JobItem v-for="(job, index) in jobList" :key="index" :job="job" />
	</div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import JobItem from "../components/JobItem.vue";
import { getAllJobs } from "@/services/GetJobViewServices";
import { JobPostDTO } from "@/model/JobPost";
import { getJobsByKeyword } from "@/services/GetJobViewServices";

export default defineComponent({
	created() {
		getAllJobs().then(list => {
			this.jobList = list;
		});
	},
	data() {
		return {
			searchText: "",
			jobList: [] as Array<JobPostDTO> | null,
		};
	},
	components: {
		JobItem,
	},
	watch: {
		searchText(searchText: string) {
			if (searchText === "") {
				getAllJobs().then(list => {
					this.jobList = list;
				});
			} else {
				getJobsByKeyword(searchText).then(list => {
					this.jobList = list;
				});
			}
		},
	},
});
</script>

<style scoped>
.job-list {
}
</style>
