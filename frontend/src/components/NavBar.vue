<template>
	<nav>
		<router-link to="/">Home</router-link> |
		<span v-if="isAuth">
			<router-link to="/hire">Hire Talent</router-link> |
		</span>
		<router-link to="/get-job">Get Job Now</router-link>
		<br />
		<br />
		<div v-if="!isAuth" class="login-register-component">
			<router-link to="/login">Login</router-link> |
			<router-link to="/register">Register</router-link>
		</div>
		<div v-if="isAuth" class="login-register-component">
			<router-link to="/" @click.prevent="logout">Logout</router-link> | <span>Hello, {{ username }}</span>
		</div>
	</nav>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import Cookies from "js-cookie";
import { MutationType } from "@/store/mutations";
import jwt_decode from "jwt-decode";
import JwtPayload from "../model/JwtPayload";

export default defineComponent({
	name: "NavBar",
	components: {},
	data() {
		return {};
	},
	computed: {
		isAuth() {
			return this.$store.state.isAuthenticated;
		},
		username(){
			const token = Cookies.get("jwt_token");
			if (token !== null && token !== undefined) {
				const decoded = jwt_decode<JwtPayload>(token);
				return decoded["firstname"];
			}
			return null;
		}
	},
	methods: {
		logout() {
			const token = Cookies.get("jwt_token");
			if (token !== null && token !== undefined) {
				try {
					this.$store.commit(MutationType.SetAuth, false);
					Cookies.remove("jwt_token")
				} catch (error) {
					console.error("Error during logout:", error);
				}
			}
		},
	},
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
	color: var(--global-green-primary);
}

.login-register-component {
}
</style>
