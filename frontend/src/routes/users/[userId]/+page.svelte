<script lang="ts">
    import type { PageData } from './$types';

    let { data }: { data: PageData } = $props();
    console.log({ data });
</script>

<h1>Hello {data.firstName} {data.lastName}</h1>
