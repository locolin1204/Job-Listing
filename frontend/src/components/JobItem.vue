<template>
	<div class="job-item">
		<div class="job-profile">
			{{ job?.profile }}
		</div>
		<div>
			<div class="job-details">
				<div class="job-desc">
					{{ job?.desc }}
				</div>
				<div>
					<span
						class="job-techs"
						v-for="(tech, index) in job?.techs"
						:key="index"
					>
						{{ tech }}
					</span>
				</div>
			</div>
		</div>
	</div>
</template>

<script lang="ts">
import { defineComponent, PropType } from "vue";
import { JobPostDTO } from "@/model/JobPost";

export default defineComponent({
	name: "JobItem",
	props: {
		job: Object as PropType<JobPostDTO>,
	},
});
</script>

<style scoped>
.job-item {
	margin: 1em;
	padding: 0.5em;
	background-color: rgb(241, 241, 241);
	display: flex;
	align-items: center;
	column-gap: 1em;
    border-radius: 0.3em;
}
.job-profile {
	width: 8em;
	background-color: rgb(255, 255, 255);
	padding: 1em 0em;
	font-weight: 700;
    border-radius: 0.3em;
}
.job-desc {
}
.job-techs {
	font-size: 0.8em;
	padding: 0.2em;
	color: rgb(97, 97, 97);

}
.job-details {
    padding: 0em 0.5em;
	display: flex;
	flex-direction: column;
    align-items: start;
}
</style>
