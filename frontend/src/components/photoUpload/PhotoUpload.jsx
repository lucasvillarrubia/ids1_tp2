import React, { useState, useCallback } from 'react'
import { Card } from "./PhUploadCard.jsx";
import { PhCardBG, PhCardButton } from './PhUpStyles'
import { apiRequest } from "../../features/uploads/uploadsAPI.js";
import { useMutation } from "@tanstack/react-query";
import { useToast } from "@/hooks/use-toast";
import { useDropzone } from "react-dropzone";
import { Upload, Camera, CloudUpload } from "lucide-react";


const PhotoUpload = ({ onSearchResults }) => {
    const [searchResults, setSearchResults] = useState();
    const { toast } = useToast();

    const uploadMutation = useMutation({
        mutationFn: async (file) => {
            const formData = new FormData();
            formData.append('image', file);

            const response = await apiRequest('POST', '/api/visual-search', formData);
            return response.json();
        },
        onSuccess: (data) => {
            setSearchResults(data.results);
            onSearchResults?.(data.results);
            toast({
                title: "Visual search completed",
                description: `Found ${data.results.length} similar products`,
            });
        },
        onError: (error) => {
            toast({
                title: "Search failed",
                description: "Please try again with a different image",
                variant: "destructive",
            });
        },
    });

    export const onDrop = useCallback((acceptedFiles) => {
        const file = acceptedFiles[0];
        if (file) {
            uploadMutation.mutate(file);
        }
    }, [uploadMutation]);

    export const { getRootProps, getInputProps, isDragActive } = useDropzone({
        onDrop,
        accept: {
            'image/*': ['.png', '.jpg', '.jpeg', '.gif', '.webp']
        },
        maxFiles: 1,
        maxSize: 10 * 1024 * 1024, // 10MB
    });

    export const handleCameraCapture = () => {
        // Create file input for camera
        const input = document.createElement('input');
        input.type = 'file';
        input.accept = 'image/*';
        input.capture = 'environment';
        input.onchange = (e) => {
            const files = (e.target).files;
            if (files && files[0]) {
                uploadMutation.mutate(files[0]);
            }
        };
        input.click();
    };

    return (
        <PhCardBG>
            <Card className="bg-white rounded-2xl shadow-xl p-8 mb-8">
                <div
                    {...getRootProps()}
                    className={`border-2 border-dashed rounded-xl p-12 text-center cursor-pointer transition-all duration-300 ${
                        isDragActive
                            ? 'border-primary bg-primary/5'
                            : 'border-gray-300 hover:border-primary hover:bg-gray-50'
                    } ${uploadMutation.isPending ? 'opacity-50 cursor-not-allowed' : ''}`}
                >
                    <input {...getInputProps()} />
                    <div className="animate-bounce-subtle mb-4">
                        {uploadMutation.isPending ? (
                            <div className="inline-flex items-center justify-center w-16 h-16 bg-primary/10 rounded-full">
                                <div className="w-6 h-6 border-2 border-primary border-t-transparent rounded-full animate-spin"></div>
                            </div>
                        ) : (
                            <CloudUpload className="w-16 h-16 text-gray-400 mx-auto" />
                        )}
                    </div>
                    <h3 className="text-lg font-semibold text-gray-900 mb-2">
                        {uploadMutation.isPending
                            ? 'Processing your image...'
                            : isDragActive
                                ? 'Drop your image here'
                                : 'Drop an image here or click to upload'
                        }
                    </h3>
                    <p className="text-gray-500 mb-6">PNG, JPG up to 10MB</p>

                    {!uploadMutation.isPending && (
                        <div className="flex flex-col sm:flex-row gap-4 justify-center">
                            <PhCardButton
                                type="button"
                                className="bg-primary text-white hover:bg-primary/90"
                                disabled={uploadMutation.isPending}
                            >
                                <Upload className="mr-2 w-4 h-4" />
                                Choose File
                            </PhCardButton>
                            <PhCardButton
                                type="button"
                                variant="outline"
                                onClick={handleCameraCapture}
                                disabled={uploadMutation.isPending}
                            >
                                <Camera className="mr-2 w-4 h-4" />
                                Take Photo
                            </PhCardButton>
                        </div>
                    )}
                </div>

                {searchResults.length > 0 && (
                    <div className="mt-8">
                        <h4 className="text-lg font-semibold text-gray-900 mb-4">Se subió la foto!</h4>
                    </div>
                )}
            </Card>
        </PhCardBG>
    )
}

export default PhotoUpload