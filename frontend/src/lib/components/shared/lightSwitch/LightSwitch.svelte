<script lang="ts">
	import { Button } from '$lib/components/ui/button';
	import { toggleMode } from 'mode-watcher';
	import IconMoon from '~icons/line-md/moon';
	import IconSun from '~icons/line-md/sunny';
</script>

<Button onclick={toggleMode} variant="outline" size="icon">
	<IconSun class="h-[1.2rem] w-[1.2rem] rotate-0 scale-100 transition-all dark:-rotate-90 dark:scale-0" />
	<IconMoon class="absolute h-[1.2rem] w-[1.2rem] rotate-90 scale-0 transition-all dark:rotate-0 dark:scale-100" />
	<span class="sr-only">Toggle theme</span>
</Button>
