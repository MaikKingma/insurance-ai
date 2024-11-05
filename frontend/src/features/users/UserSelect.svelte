<script lang="ts">
    import {goto} from "$app/navigation";
    import * as Select from "$lib/components/ui/select";

    function navigateToUserPage(userId:string) {
        goto(`/users/${userId}/`);
    }
</script>

<Select.Root onValueChange={navigateToUserPage} type="single">
    <Select.Trigger class="w-[180px]"></Select.Trigger>
    <Select.Content>
        <Select.Item value="jp">JP</Select.Item>
        <Select.Item value="maik">Maik</Select.Item>
        <Select.Item value="bart">Bart</Select.Item>
    </Select.Content>
</Select.Root>
