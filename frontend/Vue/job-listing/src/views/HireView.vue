<template>
	<div class="hire">
		<h1>Create a New Job Listing</h1>
		<form>
			<div class="job-input">
				<label>Job Profile</label>
				<input type="text" placeholder="e.g. Software Engineer" />
			</div>
			<div class="job-input">
				<label>Years of Experience</label>
				<input type="number" min="0" />
			</div>
			<div class="job-input">
				<label>Job Description</label>
				<textarea />
			</div>
			<div class="job-input">
				<label>Required Skills</label>

				<div class="input-checkbox">
					<input class="checkbox" type="checkbox" value="JavaScript" name="JavaScript" />
					<label for="JavaScript">JavaScript</label>
				</div>
				<div class="input-checkbox">
					<input class="checkbox" type="checkbox" value="Java" name="Java" />
					<label for="Java">Java</label>
				</div>
				<div class="input-checkbox">
					<input class="checkbox" type="checkbox" value="Python" name="Python" />
					<label for="Python">Python</label>
				</div>
				<div class="input-checkbox">
					<input class="checkbox" type="checkbox" value="Others" name="Others" />
					<input type="text" placeholder="Others.." />
				</div>
			</div>
            <br>
            <input type="submit" value="Submit"/>
		</form>
	</div>
</template>

<style scope>
.hire {
	display: flex;
	flex-direction: column;
	align-items: center;
}
.job-input {
	display: flex;
	flex-direction: column;
	width: 200px;
    padding: 0.5em;
}
.input-checkbox{
    display: flex;
    flex-direction: row;
    padding-right: 1em;
}
input, textarea {
    border: none;
    outline: none;
    background-color: rgb(240, 240, 240);
    border-radius: 0.25em;
    padding: 0.25em 0.5em;
    
}

.checkbox{
    margin-right: 1em;
}
label {
    font-weight: 500;
}
</style>
