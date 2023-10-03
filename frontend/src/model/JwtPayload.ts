export default interface JwtPayload {
	firstname: string;
	lastname: string;
	roles: string;
	iat: string;
	exp: number;
}
