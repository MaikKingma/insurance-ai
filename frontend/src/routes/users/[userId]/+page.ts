import { error } from '@sveltejs/kit';
import type { PageLoad } from './$types';

export const load: PageLoad = ({ params }) => {
	if (params.userId === 'jp') {
		return {
			title: 'Hi JP',
			content: 'It is you'
		};
	}

	error(404, 'Not found');
};
