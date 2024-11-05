<script lang="ts">
    import {Button} from "$lib/components/ui/button";

    const API_ENDPOINT = "http://localhost:8080";
    let output = $state("")
    let isLoading = $state(false)

    async function chat() {
        isLoading = true;
        const response = await fetch(`${API_ENDPOINT}/chat`, {
            method: "POST",
            body: JSON.stringify({message: "hello, how are you today?"})
        })

        const reader = response.body?.getReader();
        if (!reader) {
            isLoading = false;
            throw new Error("No reader found");
        }

        while (true) {
            const {done, value} = await reader.read();
            if (done) break;

            output = `${output} ${value.toString()}`
        }
        isLoading = false;
    }

</script>

<section class="grid justify-items-center content-center h-dvh p-4">
    <div>
        <h2 class="scroll-m-20 border-b pb-2 text-3xl font-semibold tracking-tight transition-colors first:mt-0">
            Insurance Perfectly Tailored for You
        </h2>
        <p class="scroll-m-20 text-xl font-semibold tracking-tight">
            Find the best policy according to your profile and preferences, powered by AI.
        </p>
        <div class="relative">
            <img class="absolute grayscale" src="/home-background-couple.png" />
            <img class="absolute" src="/home-foreground-couple.png" />
        </div>
        <Button onclick={() => chat()}>Get Started</Button>
        {#if isLoading}
            <div> loading...</div>
        {/if}
        <div>{output}</div>
    </div>
</section>
