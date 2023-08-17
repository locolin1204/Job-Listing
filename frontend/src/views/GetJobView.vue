<template>
	<div class="get-job-title">
		<h1>Get a Job!</h1>
	</div>
	<div class="job-list-tech-container">
		<div class="job-search-and-list">
			<p>Search for a job</p>
			<div class="search-bar-and-button">
				<input
					type="text"
					placeholder="e.g. Software Engineer"
					v-model="searchText"
					@keyup.enter="search"
				/>
				<!-- <XMarkIcon class="cross-icon"/> -->
				<input type="submit" value="Search" class="button" @click="search" />
			</div>
			<div class="job-list">
				<JobItem v-for="job in jobList" :key="job.id" :job="job" />
			</div>
		</div>
		<TechFilter
			:techList="techList ?? []"
			:techChosen="techChosen"
			@update-tech-chosen="updateTechChosen"
			@clear-filters="clearFilters"
		/>
	</div>
</template>


<script lang="ts">
import { XMarkIcon } from "@heroicons/vue/24/outline";
import { defineComponent } from "vue";
import JobItem from "@/components/JobItem.vue";
import { getAllJobs, getAllTechs, getJobsByKeywordAndFilter } from "@/services/GetJobViewServices";
import { JobPostDTO } from "@/model/JobPost";
import { getJobsByKeyword } from "@/services/GetJobViewServices";
import TechFilter from "@/components/TechFilter.vue";

export default defineComponent({
	created() {
		getAllJobs().then(list => {
			this.jobList = list ?? [];
		});
		getAllTechs().then(list => {
			this.techList = list ?? [];
		});
	},
	data() {
		return {
			searchText: "",
			jobList: [] as Array<JobPostDTO>,
			techList: [] as Array<string>,
			techChosen: [] as Array<string>,
		};
	},
	components: {
		JobItem,
		TechFilter,
		// XMarkIcon
	},
	watch: {
		// searchText(searchText: string) {
		// 	if (searchText === "") {
		// 		getAllJobs().then(list => {
		// 			this.jobList = list;
		// 		});
		// 	} else {
		// 		getJobsByKeyword(searchText).then(list => {
		// 			this.jobList = list;
		// 		});
		// 	}
		// },
	},
	methods: {
		search(e: Event) {
			e.preventDefault();
			console.log("big list", this.techChosen)
			getJobsByKeywordAndFilter(this.searchText, this.techChosen).then(list => {
					this.jobList = list ?? [];
				});
			}
		,
		updateTechChosen(list: Array<string>) {
			console.log("list", this.techChosen);
			this.techChosen = list;
			console.log("list", this.techChosen);
		},
		clearFilters() {
			this.techChosen = [];
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
.search-bar-and-button > input {
	margin: 1em;
}
.cross-icon {
	width: 1em;
	padding: 0em 0.5em;
	justify-content: center;
}
</style>
