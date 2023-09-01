<template>
	<div>
		<p>
			Tech Filters
			<input
				type="button"
				value="Clear Filters"
				class="button"
				@click="clearFilters"
			/>
		</p>
		<form class="job-form">
			<div class="tech-list">
				<label v-for="(tech, index) in techList" :key="index" :for="tech">
					<input
						type="checkbox"
						:name="tech"
						:id="tech"
						:value="tech"
						@change="updateTechChosen(tech)"
						v-model="checkedTechs[tech]"
					/>
					{{ tech }}
				</label>
			</div>
		</form>
	</div>
</template>

<script lang="ts">
import { JobPostDTO } from "@/model/JobPost";
import { ArrowTopRightOnSquareIcon } from "@heroicons/vue/24/outline";
import { defineComponent } from "vue";

export default defineComponent({
	name: "TechFilter",
	props: {
		techList: { type: Array as () => Array<string>, default: () => [] },
		techChosen: { type: Array as () => Array<string>, default: () => [] },
	},
	computed: {
		checkedTechs() {
			const techs = {} as Record<string, boolean>;
			this.techList.forEach(tech => {
				techs[tech] = this.techChosen.includes(tech);
			});
			return techs;
		},
	},
	methods: {
		clearFilters() {
			this.$emit("clear-filters");
		},
		updateTechChosen(tech: string) {
			const index = this.techChosen.indexOf(tech);
			const updatedTechChosen = [...this.techChosen];
			if (index === -1) {
				updatedTechChosen.push(tech);
			} else {
				updatedTechChosen.splice(index, 1);
			}
			this.$emit("update-tech-chosen", updatedTechChosen);
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
.button {
	margin: 1em;
}
</style>
