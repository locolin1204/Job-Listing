<template>
	<NavBar />
	<router-view />
</template>

<script lang="ts">
import { defineComponent } from "vue";
import NavBar from "./components/NavBar.vue";

export default defineComponent({
	name: "App",
	components: {
		NavBar,
	},
});
</script>

<style>
#app {
	font-family: Avenir, Helvetica, Arial, sans-serif;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
	text-align: center;
	color: #2c3e50;
	display: flex;
	flex-direction: column;
	align-items: center;
}
:root {
	--global-green-primary: #42b983;
	--global-green-secondary: #85cfae;
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

label {
	font-weight: 500;
}

.button{
	cursor: pointer;
}
</style>
