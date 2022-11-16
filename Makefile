up: down
	./gradlew build && docker build . -t heapkiller  && kubectl apply -f pod.yaml && sleep 3 && kubectl logs -f pod/heapkiller

down:
	kubectl delete -f pod.yaml --ignore-not-found