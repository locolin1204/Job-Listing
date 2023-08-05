<template>
	<div class="hire">
		<h1>Create a New Job Listing</h1>
		<form @submit="onSubmit" class="job-form">
			<div class="job-input">
				<label>Job Title</label>
				<input
					type="text"
					placeholder="e.g. Software Engineer"
					v-model="jobTitle"
				/>
			</div>
			<div class="job-input">
				<label>Years of Experience</label>
				<input type="number" min="0" v-model="jobExp" />
			</div>
			<div class="job-input">
				<label>Job Description</label>
				<textarea v-model="jobDesc" />
			</div>
			<div class="job-input">
				<label>Required Skills</label>
				<div v-for="(tech, index) in techs" class="input-checkbox" :key="index">
					<input
						class="checkbox"
						type="checkbox"
						:value="tech"
						:name="tech"
						v-model="reqJS"
					/>
					<label :for=tech>{{ tech }}</label>
				</div>
				<div class="input-checkbox">
					<input
						class="checkbox"
						type="checkbox"
						value="JavaScript"
						name="JavaScript"
						v-model="reqJS"
					/>
					<label for="JavaScript">JavaScript</label>
				</div>
				<div class="input-checkbox">
					<input
						class="checkbox"
						type="checkbox"
						value="Java"
						name="Java"
						v-model="reqJava"
					/>
					<label for="Java">Java</label>
				</div>
				<div class="input-checkbox">
					<input
						class="checkbox"
						type="checkbox"
						value="Python"
						name="Python"
						v-model="reqPython"
					/>
					<label for="Python">Python</label>
				</div>
				<!-- <div class="input-checkbox">
					<input class="checkbox" type="checkbox" value="Others" name="Others" />
					<input type="text" placeholder="Others.." />
				</div> -->
			</div>
			<br />
			<div v-if="isFilledIn === false" class="error-message">
				<p>Please fill in all the required fields.</p>
			</div>
			<input type="submit" value="Submit" />
		</form>
	</div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { submitJob } from "../services/HireViewServices";
import { JobPostDTO } from "../model/JobPost";

export default defineComponent({
	name: "HireView",
	data() {
		return {
			jobTitle: "",
			jobExp: 0,
			jobDesc: "",
			techs: ["JavaScript, Java, Python"],
			reqJS: false,
			reqJava: false,
			reqPython: false,
			isFilledIn: true,
		};
	},
	methods: {
		onSubmit(e: Event) {
			e.preventDefault();
			if (
				!this.jobTitle ||
				!this.jobDesc ||
				(this.reqJS === false &&
					this.reqJava === false &&
					this.reqPython === false)
			) {
				this.isFilledIn = false;
				return;
			}

			const newJob: JobPostDTO = {
				profile: this.jobTitle,
				desc: this.jobDesc,
				exp: this.jobExp,
				techs: [],
			};

			submitJob(newJob);

			this.jobTitle = "";
			this.jobDesc = "";
			this.jobExp = 0;
			this.reqJS = false;
			this.reqJava = false;
			this.reqPython = false;
		},
	},
});
</script>

<style scope>
.job-form {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
}
.job-input {
	display: flex;
	flex-direction: column;
	width: 200px;
	padding: 0.5em;
}
.input-checkbox {
	display: flex;
	flex-direction: row;
	padding-right: 1em;
}
input,
textarea {
	border: none;
	outline: none;
	background-color: rgb(240, 240, 240);
	border-radius: 0.25em;
	padding: 0.25em 0.5em;
	font-family: inherit;
}

.checkbox {
	margin-right: 1em;
}
label {
	font-weight: 500;
}
.error-message {
	color: red;
}
</style>
