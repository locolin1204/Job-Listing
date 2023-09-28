<template>
    <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/hire">Hire Talent</router-link> |
    <router-link to="/get-job">Get Job Now</router-link> |
    <router-link to="/login">Login</router-link> |
    <router-link to="/register">Register</router-link>
  </nav>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
	name: "NavBar",
	components: {},
});
</script>

<style scoped>

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
  text-decoration: none;
}

nav a.router-link-exact-active {
  color: var(--global-green);
}
</style>