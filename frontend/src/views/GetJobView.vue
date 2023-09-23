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
				<div
					class=""
					v-if="
						isGetJobsByKeywordAndFilterSent === true &&
						isGetJobsByKeywordAndFilterStatus == null
					"
				>
					Loading...
				</div>
				<!-- if jobList.length === 0 and response unknown -> loading screen -->
				<!-- if jobList.length === 0 AND response === 200 -> "no such result" -->
				<div class="no-result" v-if="jobList.length === 0">No Such Result!</div>
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
import {
	getAllJobs,
	getAllTechs,
	jobsByKeywordAndFilterResponse,
} from "@/services/GetJobViewServices";
import { JobPostDTO } from "@/model/JobPost";
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
			isGetJobsByKeywordAndFilterSent: false, //here
			isGetJobsByKeywordAndFilterStatus: null as number | null,
		};
	},
	components: {
		JobItem,
		TechFilter,
		// XMarkIcon
	},
	watch: {},
	methods: {
		async search(e: Event) {
			try {
				e.preventDefault();
				this.isGetJobsByKeywordAndFilterSent = true;
				const res = await jobsByKeywordAndFilterResponse(
					this.searchText,
					this.techChosen
				);
				this.jobList = res.data ?? [];
				this.isGetJobsByKeywordAndFilterStatus = res.status;
				console.log(res.status);
			} catch (error) {
				console.error(error);
			}
			this.isGetJobsByKeywordAndFilterStatus = null;
			this.isGetJobsByKeywordAndFilterSent = false;
		},
		updateTechChosen(list: Array<string>) {
			this.techChosen = list;
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

@keyframes horizontal-shaking {
	0% {
		transform: translateX(0);
	}
	25% {
		transform: translateX(5px);
	}
	50% {
		transform: translateX(-5px);
	}
	75% {
		transform: translateX(5px);
	}
	100% {
		transform: translateX(0);
	}
}
.no-result {
	padding: 2em;
	animation: 0.25s ease-out 0s 1 horizontal-shaking;
}
</style>
