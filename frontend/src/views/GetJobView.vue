<template>
	<div class="get-job-title">
		<h1>Get a Job!</h1>
	</div>
	<div class="job-list-tech-container">
		<div class="job-search-and-list">
			<p>Search for a job</p>
			<input
				type="text"
				placeholder="e.g. Software Engineer"
				v-model="searchText"
			/>
			<div class="job-list">
				<JobItem v-for="job in jobList" :key="job.id" :job="job" />
			</div>
		</div>
		<TechFilter />
	</div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import JobItem from "@/components/JobItem.vue";
import { getAllJobs } from "@/services/GetJobViewServices";
import { JobPostDTO } from "@/model/JobPost";
import { getJobsByKeyword } from "@/services/GetJobViewServices";
import TechFilter from "@/components/TechFilter.vue";

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
		TechFilter,
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
.red {
	border: red 1px solid;
}
.job-list-tech-container {
	display: flex;
	flex-direction: row;
	/* border: red 1px solid; */
}
.job-list {
	width: 50em;
}
</style>
