<template>
	<div>
		<h1>Login</h1>
		<form @submit="onSubmit">
			<div class="username input-field">
				<label>Email</label>
				<input type="text" placeholder="e.g. abc@gmail.com" v-model="email" />
			</div>

			<div class="password input-field">
				<label>Password</label>
				<input type="password" placeholder="" v-model="password" />
			</div>
			<input type="submit" value="Login" class="login-button button" />
		</form>
		<div v-if="isLoginFailed">Failed</div>
	</div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { userLogin, verifyToken } from "@/services/LoginViewService";
import Cookies from "js-cookie";
import { MutationType } from "@/store/mutations";

export default defineComponent({
	name: "LoginView",
	async created() {
		const token = Cookies.get("jwt_token");
		if (token !== null && token !== undefined) {
			try {
				const res = await verifyToken(token);
				console.log(res);
				if (res !== null && res.data ) {
					this.$store.commit(MutationType.SetAuth, true)
					await this.$router.push("/");
				} else {
					Cookies.remove("jwt_token");
					this.$store.commit(MutationType.SetAuth, false)
				}
			} catch (error) {
				console.error("Error during login:", error);
			}
		}
	},
	data() {
		return {
			email: `${process.env.VUE_APP_DEFAULT_EMAIL}`,
			password: `${process.env.VUE_APP_DEFAULT_PASSWORD}`,
			isLoginFailed: false,
		};
	},
	methods: {
		async onSubmit(e: Event) {
			this.isLoginFailed = false;
			e.preventDefault();
			const token = Cookies.get("jwt_token");
			if (token !== null) {
				console.log("token is null");
			}
			try {
				const res = await userLogin(this.email, this.password);
				if (res) {
					const token = res.data.token;
					Cookies.set("jwt_token", token);
					this.$store.commit(MutationType.SetAuth, true);
					await this.$router.push("/");
				} else {
					this.$store.commit(MutationType.SetAuth, false);
					this.isLoginFailed = true;
					console.error("Login Failed");
				}
			} catch (error) {
				console.error("Error during login:", error);
			}
		},
	},
});
</script>

<style scoped>
form {
	display: flex;
	flex-direction: column;
	row-gap: 1em;
}

.input-field > input {
	display: flex;
	flex-direction: column;
	width: 15em;
}

.login-button {
	font-weight: bold;
	font-size: 1.1em;
	/* padding: 1em; */
	background-color: var(--global-green-primary);
	color: white;
	/* width:100%; */
	margin: 0;
}

.login-button:hover {
	background-color: var(--global-green-secondary);
}
</style>
