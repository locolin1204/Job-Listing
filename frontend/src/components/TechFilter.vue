<template>
	<div>
		<p>Tech Filters</p>
		<form @submit="onSubmit" class="job-form">
			<div class="tech-list">
				<label v-for="(tech, index) in techList" :key="index" :for="tech">
					<input
						type="checkbox"
						:name="tech"
						:id="tech"
						:value="tech"
						v-model="techChosen"
					/>
                    {{ tech }}
				</label>
			</div>
			<input type="submit" value="Submit" class="submit-button"/>
		</form>
	</div>
</template>

<script lang="ts">
import { JobPostDTO } from "@/model/JobPost";
import { filterTechPosts, getAllTechs } from "@/services/GetJobViewServices";
import { ArrowTopRightOnSquareIcon } from "@heroicons/vue/24/outline";
import { defineComponent } from "vue";

export default defineComponent({
	name: "TechFilter",
	created() {
		getAllTechs().then(list => {
			this.techList = list;
			this.techList = ["Java", "Swift", "Kotlin"];
		});
	},
	data() {
		return {
			techList: [] as Array<string> | null,
			techChosen: [] as Array<string> | null,
			jobPostWithFilter: [] as Array<JobPostDTO> | null,
		};
	},
	methods: {
		async onSubmit(e: Event) {
			e.preventDefault();
			filterTechPosts();
		},
	},
});
</script>

<style>
.red {
	border: 1px red solid;
}
.tech-list {
	display: flex;
    flex-direction: column;
    align-items: start;
}
.submit-button {
    margin: 1em;
}
</style>
