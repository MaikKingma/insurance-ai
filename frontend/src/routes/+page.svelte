<script lang="ts">
    import {Configuration, UserQueriesApi} from "$lib/api";
    import HeroBanner from "$lib/components/shared/heroBanner/HeroBanner.svelte";
    import {Button} from "$lib/components/ui/button";
    import {apiConfig} from "$lib/utils";

    let output = $state("")
    let isLoading = $state(false)

    const usersApi = new UserQueriesApi(apiConfig);

    async function chat() {
        isLoading = true;

        const allUsers = await usersApi.getAllUsers()
        console.log(allUsers)
        isLoading = false;
        return;

        // const response = await fetch(`${API_ENDPOINT}/chat`, {
        //     method: "POST",
        //     body: JSON.stringify({message: "hello, how are you today?"})
        // })
        //
        // const reader = response.body?.getReader();
        // if (!reader) {
        //     isLoading = false;
        //     throw new Error("No reader found");
        // }
        //
        // while (true) {
        //     const {done, value} = await reader.read();
        //     if (done) break;
        //
        //     output = `${output} ${value.toString()}`
        // }
        // isLoading = false;
    }

</script>

<HeroBanner />
