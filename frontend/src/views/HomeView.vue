<template>
	<div class="home">The <span>Green</span> Project!</div>
	<br />
	<div>
		<i class="devicon-vuejs-plain colored"></i>
		<i class="devicon-mongodb-plain colored"></i>
		<i class="devicon-spring-plain colored"></i>
	</div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
	name: "HomeView",
	components: {},
});
</script>

<style scoped>
.colored {
	font-size: 1.5em;
	color: var(--global-green-primary);
}
span {
	font-weight: 700;
	color: var(--global-green-primary);
}
</style>
