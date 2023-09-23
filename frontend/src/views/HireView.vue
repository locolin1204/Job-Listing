<template>
	<div class="hire">
		<h1>Create a New Job Listing</h1>
		<form @submit="onSubmit" class="job-form">
			<div class="job-input">
				<label>Job Title</label>
				<input
					type="text"
					placeholder="e.g. Software Engineer"
					v-model="jobProfile"
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
					<label :for="tech">
						<input
							class="checkbox"
							type="checkbox"
							:value="tech"
							:name="tech"
							:id="tech"
							v-model="techsRequired"
						/>
						{{ tech }}
					</label>
				</div>
			</div>
			<br />
			<div v-if="isFilledIn === false" class="error-message">
				<p>Please fill in all the required fields.</p>
			</div>
			<div>
				<div class="submit-message">
					<input type="submit" value="Submit" class="submit-button" />
					<div class="status-icon" v-if="isSent == true">
						<ArrowPathIcon
							v-if="submitJobRes === null && !isError"
							class="loading-icon icon"
						/>
						<CheckCircleIcon
							v-if="submitJobRes?.status === 201 && !isError"
							class="success-icon"
						/>
						<ExclamationTriangleIcon v-if="isError" class="warning-icon" />
					</div>
				</div>
			</div>
		</form>
	</div>
</template>

<script lang="ts">
import {
	ExclamationTriangleIcon,
	CheckCircleIcon,
	ArrowPathIcon,
} from "@heroicons/vue/24/outline";
import { defineComponent } from "vue";
import { submitJobAndResponse } from "../services/HireViewServices";
import { JobPostDTO } from "../model/JobPost";
import { ref } from "vue";
import { StatusCodes } from "http-status-codes";
import { AxiosResponse } from "axios";

export default defineComponent({
	name: "HireView",
	setup() {
		const techs = ref(["JavaScript", "Java", "Python", "React"]);

		return {
			techs,
		};
	},
	data() {
		return {
			jobProfile: "",
			jobExp: 0,
			jobDesc: "",
			techsRequired: [] as string[],
			isFilledIn: true,
			isSent: false,
			isError: false,
			submitJobRes: null as AxiosResponse | null,
		};
	},
	components: { ExclamationTriangleIcon, CheckCircleIcon, ArrowPathIcon },
	methods: {
		async onSubmit(e: Event) {
			this.isSent = false;
			e.preventDefault();
			if (!this.jobProfile || !this.jobDesc || this.techsRequired.length == 0) {
				this.isFilledIn = false;
				return;
			}
			this.isFilledIn = true;

			const newJob: JobPostDTO = {
				profile: this.jobProfile,
				desc: this.jobDesc,
				exp: this.jobExp,
				techs: this.techsRequired,
			};

			this.isSent = true;
			try {
				this.submitJobRes = await submitJobAndResponse(newJob);
				if (this.submitJobRes.status === StatusCodes.OK) {
					this.jobProfile = "";
					this.jobDesc = "";
					this.jobExp = 0;
					this.techsRequired = [];
				}
			} catch (error) {
				this.isError = true;
				console.error(error);
			}
		},
	},
});
</script>

<style scope>
div {
	--warning-red-color: rgb(255, 81, 81);
}
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
.input-checkbox > label {
	cursor: pointer;
}
.error-message {
	color: var(--warning-red-color);
}
.submit-button,
.checkbox,
.button {
	cursor: pointer;
}
.submit-button:hover,
.button:hover {
	background-color: rgb(227, 227, 227);
}
.submit-message {
	display: flex;
	flex-direction: row;
	align-items: center;
}
.status-icon {
	display: flex;
	width: 1em;
	padding: 0em 0.5em;
	justify-content: center;
}
.warning-icon {
	color: var(--warning-red-color);
}
.success-icon {
	color: var(--global-green);
}

@keyframes rotating {
	from {
		-webkit-transform: rotate(0deg);
	}
	to {
		-webkit-transform: rotate(360deg);
	}
}

.loading-icon {
	animation: rotating 2s linear infinite;
}

.icon {
}
</style>
