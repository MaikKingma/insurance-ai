<script lang="ts">
    import LightSwitch from "$lib/components/shared/lightSwitch/LightSwitch.svelte";
    import LineMdCloudAltPrintFilledLoop from '~icons/line-md/cloud-alt-print-filled-loop';
    import { ModeWatcher } from "mode-watcher";
	import '../app.css';
    import UserSelect from "../features/users/UserSelect.svelte";
	let { children } = $props();
</script>

<ModeWatcher />
<header class="w-full px-4 py-2 border-b flex items-center justify-between">
    <h1 class="scroll-m-20 text-2xl font-semibold tracking-tight align-bottom">
        <a href="/"><LineMdCloudAltPrintFilledLoop class="inline-block -translate-y-1 pr-1" /> INSURANCE.AI</a>
    </h1>
    <div class="flex gap-2">
        <UserSelect />
        <LightSwitch />
    </div>
</header>
<main>
{@render children?.()}
</main>
