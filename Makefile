go:
	./gradlew build && docker build . -t heapkiller && kubectl delete -f pod.yaml --ignore-not-found && kubectl apply -f pod.yaml && sleep 3 && kubectl logs -f pod/heapkiller