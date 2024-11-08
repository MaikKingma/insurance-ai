<script lang="ts">
	import * as Card from '$lib/components/ui/card';
	import { Button } from '$lib/components/ui/button';
	import { Input } from '$lib/components/ui/input';
	import { Label } from '$lib/components/ui/label';

</script>

<Card.Root>
	<Card.Header class="space-y-1">
		<Card.Title class="text-2xl">Create an account</Card.Title>
		<Card.Description>Enter your email below to create your account</Card.Description>
	</Card.Header>
	<Card.Content class="grid gap-4">
		<div class="grid gap-2">
			<Label for="username">Username</Label>
			<Input id="username" name="username" placeholder="henk" />
		</div>
		<div class="grid gap-2">
			<Label for="firstName">First name</Label>
			<Input id="firstName" name="firstName" />
		</div>
		<div class="grid gap-2">
			<Label for="lastName">Last name</Label>
			<Input id="lastName" name="lastName"/>
		</div>
	</Card.Content>
	<Card.Footer>
		<Button class="w-full" type="submit">Create account</Button>
	</Card.Footer>
</Card.Root>
