<script lang="ts">
    import {goto, onNavigate} from "$app/navigation";
    import {UserQueriesApi, type UserView} from "$lib/api/user";
    import * as Select from "$lib/components/ui/select";
    import {apiConfig} from "$lib/utils";

    function navigateToUserPage(userId:string) {
        goto(`/users/${userId}/`);
    }

    let users = $state<UserView[]>([])
    const api = new UserQueriesApi(apiConfig);
    function updateUsers() {
        api.getAllUsers().then(response => {
            users = response;
        })
    }
    updateUsers()
    onNavigate(updateUsers)
</script>

<Select.Root onValueChange={navigateToUserPage} type="single">
    <Select.Trigger class="w-[180px]"></Select.Trigger>
    <Select.Content>
        {#each users as user}
        <Select.Item value={user.id?.toString()}>{user.username}</Select.Item>
            {/each}
    </Select.Content>
</Select.Root>
