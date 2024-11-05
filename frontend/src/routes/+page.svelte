<script>
import {Button} from "$lib/components/ui/button";

const API_ENDPOINT = "http://localhost:8080";
let output = $state("")
let isLoading = $state(false)

async function chat() {
    isLoading = true;
    const response = await fetch(`${API_ENDPOINT}/chat`, {method: "POST", body: JSON.stringify({ message: "hello, how are you today?"})})

    const reader = response.body?.getReader();
    if(!reader) {
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
        <h1>press the button</h1>
        <Button onclick={() => chat()}>click me</Button>
        {#if isLoading}
            <div> loading...</div>
            {/if}
        <div>{output}</div>
    </div>
</section>
