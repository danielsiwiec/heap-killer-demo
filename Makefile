go:
	./gradlew build && docker build . -t heapkiller && kubectl delete -f job.yaml --ignore-not-found && kubectl apply -f job.yaml && sleep 3 && kubectl logs -f job.batch/heapkiller