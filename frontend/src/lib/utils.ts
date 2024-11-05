import { Configuration } from '$lib/api';
import { type ClassValue, clsx } from 'clsx';
import { twMerge } from 'tailwind-merge';

export function cn(...inputs: ClassValue[]) {
	return twMerge(clsx(inputs));
}

const API_ENDPOINT = 'http://localhost:8080';

export const apiConfig = new Configuration({ basePath: API_ENDPOINT });
