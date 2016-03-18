Stockontrol.directive('validateUserEmail', function($q)
{
	return {
		require: 'ngModel',
		link: function(scope, el, attrs, controller)
		{
			controller.$asyncValidators.validateUserEmail = function(val)
			{
				if(controller.$isEmpty(val))
				{
					return $q.when();
				}
				var userId = scope.user.id;
				console.log('userId: ', userId);
				console.log('val: ', val);
				var def = $q.defer();

				userService.findByEmail(val, function(user)
				{
					console.log('User: ', user);
					// Se for o mesmo usuário dá nada
					if(user === null || user.id === userId)
					{
						def.resolve();
					}
					else
					{
						def.reject();
					}
				});
				return def.promise;
			};
		},
	};
});
