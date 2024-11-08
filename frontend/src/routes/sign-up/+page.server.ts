import { UserCommandsApi } from '$lib/api';
import { apiConfig } from '$lib/utils';
import { fail, redirect } from '@sveltejs/kit';
import type { Actions } from './$types';

export const actions = {
	default: async ({ request }) => {
		const userCommands = new UserCommandsApi(apiConfig);

		const data = await request.formData();
		const username = data.get('username');
		const firstName = data.get('firstName');
		const lastName = data.get('lastName');

		if (!username) {
			return fail(400, { username, missingUsername: true });
		}
		if (!firstName) {
			return fail(400, { firstName, missingFirstName: true });
		}
		if (!lastName) {
			return fail(400, { lastName, missingLastName: true });
		}

		const user = await userCommands.signUpUser({
			userSignUpPayload: {
				username: username as string,
				firstName: firstName as string,
				lastName: lastName as string
			}
		});

		redirect(303, `/users/${user.id}`);
	}
} satisfies Actions;
