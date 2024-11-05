<script lang="ts">
    import {goto} from "$app/navigation";
    import {UserCommandsApi} from "$lib/api";
    import * as Card from '$lib/components/ui/card'
    import {Button} from "$lib/components/ui/button";
    import {Input} from "$lib/components/ui/input";
    import {Label} from "$lib/components/ui/label";
    import {apiConfig} from "$lib/utils";

    const userCommands = new UserCommandsApi(apiConfig)
    const formFields = $state({
        username: "",
        firstName: "",
        lastName: "",
    })

    function submit() {
        userCommands.signUpUser({
            userSignUpPayload: {
                username: formFields.username,
                firstName: formFields.firstName,
                lastName: formFields.lastName
            }
        }).then(user => {
            goto(`/users/${user.id}`)
        })
    }
</script>
<Card.Root>
    <Card.Header class="space-y-1">
        <Card.Title class="text-2xl">Create an account</Card.Title>
        <Card.Description>Enter your email below to create your account</Card.Description>
    </Card.Header>
    <Card.Content class="grid gap-4">
        <div class="grid gap-2">
            <Label for="username">Username</Label>
            <Input id="username" type="text" bind:value={formFields.username} placeholder="henk"/>
        </div>
        <div class="grid gap-2">
            <Label for="firstname">First name</Label>
            <Input id="firstname" bind:value={formFields.firstName} type="text"/>
        </div>
        <div class="grid gap-2">
            <Label for="lastname">Last name</Label>
            <Input id="lastname" bind:value={formFields.lastName} type="text"/>
        </div>
    </Card.Content>
    <Card.Footer>
        <Button class="w-full" onclick={submit}>Create account</Button>
    </Card.Footer>
</Card.Root>
