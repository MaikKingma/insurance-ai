import { UserQueriesApi } from '$lib/api';
import { apiConfig } from '$lib/utils';
import { error } from '@sveltejs/kit';
import type { PageLoad } from './$types';

export const load: PageLoad = async ({ params }) => {
	const userApi = new UserQueriesApi(apiConfig);
	return await userApi.getUserById({ id: Number(params.userId) }).catch(() => {
		error(404, 'Not found');
	});
};
