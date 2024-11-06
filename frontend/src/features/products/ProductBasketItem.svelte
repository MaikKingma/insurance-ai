<script lang="ts">
    import {fly} from "svelte/transition";
    const data = $props()
</script>


<div in:fly={{ y: 100 }}>{@render data.children?.()}</div>
